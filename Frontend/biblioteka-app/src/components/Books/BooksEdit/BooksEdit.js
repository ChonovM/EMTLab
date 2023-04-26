import React from 'react';
import {useNavigate} from 'react-router-dom';

const BooksEdit = (props) => {
    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "",
        authors: 1,
        availableCopies: 0
    })

    console.log(props.books);

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.books.name;
        const category = formData.category !== "" ? formData.category : props.books.category;
        const authorsId = formData.authors !== 0 ? formData.authors : props.books.authors.id;
        const availableCopies = formData.availableCopies !== "" ? formData.availableCopies : props.books.availableCopies;
        props.onEditBook(props.books.id, name, category, authorsId, availableCopies);
        navigate("/books");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book Name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder={props.books.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="authors" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                if(props.books.authors !== undefined && props.books.authors.id === term.id){
                                    return <option selected={term.id}>{term.name}</option>
                                }else{
                                    return <option value={term.id}>{term.name}</option>
                                }
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) => {
                                if(props.books.category !== undefined && props.books.category === term){
                                    return <option selected={props.books.category} value={term}>{term}</option>
                                }else{
                                    return <option value={term}>{term}</option>
                                }
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="number"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               required
                               placeholder={props.books.availableCopies}
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default BooksEdit;