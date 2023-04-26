import React from 'react';

const categories = (props) => {
    return(
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-bordered"}>
                        <thead>
                        <tr className={"table-secondary text-dark"}>
                            <th>Categories</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.categories.map((term) => {
                            return(
                                <tr className={"table-dark"}>
                                    <td>{term}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default categories;
