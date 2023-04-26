import axios from '../custom-axios/axios';

const eBibliotekaService = {
    fetchBook: () => {
        return axios.get("/books");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    },
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, category, authorID, availableCopies) => {
        return axios.post("/books/add", {
            "name" : name,
            "category" : category,
            "author" : authorID,
            "availableCopies" : availableCopies
        });
    },
    editBook: (id, name, category, authorID, availableCopies) => {
        return axios.put(`/books/edit/${id}`,{
            "name" : name,
            "category" : category,
            "author" : authorID,
            "availableCopies" : availableCopies
        });
    },
    rentBook: (id) => {
        return axios.put(`/books/rent/${id}`);
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    }
}

export default eBibliotekaService;