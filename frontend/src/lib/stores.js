import { derived, writable } from "svelte/store";


export const authToken = writable(localStorage.getItem('jwt_token'));
authToken.subscribe(value => {
    if (value) localStorage.setItem('jwt_token', value);
    else localStorage.removeItem('jwt_token')
});

export const alerts = writable([]);

export function addAlert(message, style) {
    const id = Math.random();

    alerts.update((prev) => [
        ...prev,
        { id, message, style }
    ]);

    setTimeout(() => {
        removeAlert(id);
    }, 5000);
}

function removeAlert(id) {
    alerts.update((prev) => prev.filter((alert) => alert.id !== id));
}



export const currentUser = derived(authToken, ($auth) => {
    try {
        const payload = JSON.parse(atob($auth.split(".")[1]));
        return payload.sub || "ERROR";
    } catch (err) {
        console.error("Error with the token username fetch");
        return null;
    }
});



