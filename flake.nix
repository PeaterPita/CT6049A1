{
  description = "Nix-Flake based Java Dev Shell";
  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";

  outputs =
    inputs:
    let
      javaVersion = 21;
      system = "x86_64-linux";

      run-appScript = pkgs.writeShellScriptBin "run-app" ''
        trap "kill 0" SIGINT SIGTERM EXIT

        PROFILE=''${1:-sql} 

        echo "-------------------------------------------------"
        echo "  STARTING LIBRARY SYSTEM (Profile: $PROFILE)"
        echo "-------------------------------------------------"

        if ! sudo docker ps | grep -q "postgres"; then
            echo "Starting Docker containers..."
            sudo docker compose up -d > /dev/null 2>&1
            sleep 3
        fi

        echo "Starting Backend..."
        (cd backend && mvn spring-boot:run -Dspring-boot.run.profiles=$PROFILE > ../backend.log 2>&1) &

        echo "Starting Frontend..."
        (cd frontend && npm run dev -- > ../frontend.log 2>&1) &

        echo "-------------------------------------------------"
        echo "   Application Running!"
        echo "   Frontend: http://localhost:5173"
        echo "   Backend:  http://localhost:8082"
        echo "-------------------------------------------------"

        wait
      '';

      pkgs = import inputs.nixpkgs {
        inherit system;
        overlays = [ overlay ];
      };

      overlay =
        final: prev:
        let
          jdk = prev."jdk${toString javaVersion}";
        in
        {
          inherit jdk;
          maven = prev.maven.override { jdk_headless = jdk; };
        };

    in
    {

      devShells.${system}.default = pkgs.mkShell {
        packages = with pkgs; [
          gcc
          nodejs
          jdk
          maven
          zlib
          run-appScript
        ];

        nativeBuildInputs = with pkgs; [ pkg-config ];

        shellHook = ''
            export JAVA_HOME=${pkgs.jdk};
          echo "Java Devshell ready ($(java --version)) avilable in path"
        '';
      };
    };
}
