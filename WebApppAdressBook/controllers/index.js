import { response, request } from "express";
import fetch from "node-fetch";

export const renderIndex = (req = request, res = response) => {

    // fetch("localhost:8080/")
    //     .then((res) => res.json())
    //     .then((log) => console.log(log));

    res.render("index.hbs");
};
