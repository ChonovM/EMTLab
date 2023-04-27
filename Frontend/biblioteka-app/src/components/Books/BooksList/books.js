import React from "react";
import ReactPaginate from "react-paginate";
import BooksTerm from "../BooksTerm/BooksTerm";
import {Link} from "react-router-dom";

class Books extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 5
        }
    }

    render() {

        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size);
        const books = this.getBooksPage(offset, nextPageOffset);

        return(
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"row"}>
                        <table className={"table table-striped table-bordered text-center"}>
                            <thead>
                            <tr className={"table-secondary text-dark"}>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Category</th>
                                <th scope={"col"}>Author</th>
                                <th scope={"col"}>Copies</th>
                                <th scope={"col"}>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {books}
                            </tbody>
                        </table>
                    </div>
                    <div >
                        <div>
                            <div className={"d-flex justify-content-center"}>
                                <Link className={"btn btn-dark btn-lg"} to={"/books/add"}>Add Book</Link>
                            </div>
                        </div>
                    </div>
                    <div>
                        <ReactPaginate
                            previousLabel={"Back"}
                            nextLabel={"Next"}
                            breakLabel={<a href={"/#"}>...</a>}
                            breakClassName={"break-me"}
                            pageClassName={"ml-1"}
                            pageCount={pageCount}
                            marginPagesDisplayed={2}
                            pageRangeDisplayed={5}
                            onPageChange={this.handlePageSwitch}
                            containerClassName={"pagination pagination-lg m-4 justify-content-center"}
                            activeClassName={"active"}
                            disabledClassName={"disabled"}
                            previousClassName={"page-item"}
                            nextClassName={"page-item"}
                            previousLinkClassName={"page-link"}
                            nextLinkClassName={"page-link"}
                            pageLinkClassName={"page-link"}
                        />
                    </div>
                </div>
            </div>
        )
    }

    handlePageSwitch = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }
    getBooksPage = (offset, nextPageOffset) => {
        return this.props.books.map((term, index) => {
            return(
                <BooksTerm term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit} onRent={this.props.onRent}/>
            );
        }).filter((books, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}

export default Books;
