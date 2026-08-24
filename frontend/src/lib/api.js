import { authToken } from "./stores";

const API_BASE = 'http://localhost:8082';
let token;

authToken.subscribe(value => {
    token = value;
})

async function request(path, opts = {}) {

    const url = API_BASE + path;
    const headers = opts.headers ? { ...opts.headers } : {};


    if (token) headers['Authorization'] = 'Bearer ' + token;
    headers['Content-Type'] = headers['Content-Type'] || 'application/json';


    const res = await fetch(url, { ...opts, headers });
    if (res.status === 401) {
        authToken.set(null);
        throw new Error("Unauthorized");
    }

    let data;
    const contentType = res.headers.get('content-type') || '';
    if (contentType.includes('application/json')) {
        data = await res.json();
    } else {
        data = await res.text();
    }

    if (!res.ok) {
        const err = (typeof data === 'object' && data.error) ? data.error : (data || res.statusText);
        throw new Error(err);
    }

    return data;
}



export async function login(username, password) {
    const res = await fetch(API_BASE + '/api/auth/login', {
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    });
    if (!res.ok) {
        const err = await res.json().catch(() => ({ message: 'Login failed' }));
        throw new Error(err.message || 'bad');
    }
    const body = await res.json();
    return body.token;
}

export async function fetchBooks() {
    return request('/api/books');
}

export async function searchBooks(query = '', available = false) {
    const urlBuild = new URLSearchParams();
    if (query && query.trim().length > 0) urlBuild.append("query", query.trim());
    if (available) urlBuild.append('available', 'true');

    const path = '/api/books' + (urlBuild.toString() ? ('?' + urlBuild.toString()) : '');
    return request(path);
}

export async function fetchBookById(id) {
    return request(`/api/books/${id}`);
}



export async function fetchLoansForUser() {
    return request('/api/loans/me');


}

export async function borrowBook(bookId) {
    return request('/api/loans/borrow', {
        method: 'POST',
        body: JSON.stringify({ bookId })
    });
}

export async function returnBook(loanId) {
    return request('/api/loans/return', {
        method: 'POST',
        body: JSON.stringify({ loanId })
    });
}

export async function payFine(loanId) {
    return request(`/api/loans/pay/${loanId}`, { method: 'POST' });
}





