import { response, request } from "express";
import axios from "axios";

/**
 * Contains the contact found by id in the database
 */
var contact;

/**
 * Handdle the API GET request 
 * and render in the view the contact recived from database
 * @param {request} req Contains the server request
 * @param {response} res Contains the server serponse
 */
export const renderEdit = async (req = request, res = response) => {
    let { id } = req.params;

    await axios
        .get(`http://localhost:8080/api/v1/find/${id}`)
        .then((res) => (contact = res.data.data))
        .catch((err) => console.log(err.response.data.message));

    res.render("edit-index.hbs", { contact, id });
};

/**
 * Handdle the API POST request
 * and redirects to the index view
 * @param {request} req Contains the server request
 * @param {response} res Contains the server serponse
 */
export const postEdit = async (req = request, res = response) => {
    let data = req.body;
    let { id } = req.params;

    await axios
        .put(`http://localhost:8080/api/v1/address_book/${id}`, data)
        .catch((err) => console.log(err));

    res.redirect("/");
};
