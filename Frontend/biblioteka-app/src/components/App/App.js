import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import Books from "../Books/BooksList/books";
import Categories from  "../Categories/categories";
import Header from '../Header/header';
import BooksAdd from '../Books/BooksAdd/booksAdd';
import BooksEdit from '../Books/BooksEdit/BooksEdit'
import eBibliotekaService from "../../repository/eBibliotekaRepository";
import 'bootstrap/dist/css/bootstrap.min.css';


class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            authors: [],
            categories: [],
            selectedBook: {}
        }
    }

    render() {
        return(
                <Router>
                    <Header>
                    </Header>
                    <main>
                        <div className="container">
                            <Routes>
                                <Route path={"/categories"} exact element={<Categories categories={this.state.categories}/>}/>
                                <Route path={"/books/add"} exact element={<BooksAdd categories={this.state.categories} authors={this.state.authors} onAddBook={this.addBook}/>}/>
                                <Route path={"/books/edit/:id"} exact element={<BooksEdit categories={this.state.categories} authors={this.state.authors} onEditBook={this.editBook} books={this.state.selectedBook}/>}/>
                                <Route path={"/books"} exact element={<Books books={this.state.books} onDelete={this.deleteBook} onEdit={this.getBook} onRent={this.rentBook}/>}/>
                                <Route path={"/"} element={<Navigate replace to={"/books"}/>}/>
                            </Routes>
                        </div>
                    </main>
                </Router>
        );
    }

    loadBooks = () => {
        eBibliotekaService.fetchBook()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            })
    }

    loadCategories = () => {
        eBibliotekaService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            })
    }

    loadAuthors = () => {
        eBibliotekaService.fetchAuthors()
            .then((data) =>{
                this.setState({
                    authors: data.data
                })
            })
    }

    deleteBook = (id) => {
        eBibliotekaService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            })
    }

    addBook = (name, category, authorID, availableCopies) => {
        eBibliotekaService.addBook(name, category, authorID, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        eBibliotekaService.getBook(id)
            .then((data) => {
                console.log(data);
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, authorID, availableCopies) => {
        eBibliotekaService.editBook(id, name, category, authorID, availableCopies)
            .then(() => {
                this.loadBooks()
            });
    }

    rentBook = (id) =>{
        eBibliotekaService.rentBook(id).then(() =>
            {
                this.loadBooks();
            }
        )
    }

    componentDidMount() {
        this.loadBooks();
        this.loadCategories();
        this.loadAuthors()
    }


}

export default App;
