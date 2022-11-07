import { response, request } from "express";
import axios from "axios";

/**
 * Array that contains the objects recived from database
 */
var contacts = [];

/**
 * Handdle the API GET request 
 * and render in the view the contacts recived from database
 * @param {request} req Contains the server request
 * @param {response} res Contains the server serponse
 */
export const renderIndex = async (req = request, res = response) => {
    await axios
        .get("http://localhost:8080/api/v1/index")
        .then((res) => (contacts = res.data.data))
        .catch((err) => console.log(err.response.data.message));

    res.render("index.hbs", { contacts });
};

/**
 * Handdle the API POST request
 * and redirects to the index view
 * @param {request} req Contains the server request
 * @param {response} res Contains the server serponse
 */
export const postIndex = async (req = request, res = response) => {
    let data = req.body;

    await axios
        .postForm("http://localhost:8080/api/v1/address_book", data)
        .catch((err) => console.log(err));

    res.redirect("/");
};

/**
 * Handdle the API DELETE request
 * and redirects to the index view
 * @param {request} req Contains the server request
 * @param {response} res Contains the server serponse
 */
export const deleteIndex = async (req = request, res = response) => {
    const { id } = req.params;

    await axios
        .delete(`http://localhost:8080/api/v1/address_book/${id}`)
        .catch((err) => console.log(err));

    res.redirect("/");
};
