import express from "express";
import {router as routerIndex} from "../routes/index.js";
import {router as routerEdit} from "../routes/edit.js"
import "hbs";

/**
 * Server definition of the web application
 */
export default class Server {
    /**
     * constructor
     */
    constructor() {
        this.app = express();
        this.port = process.env.PORT || 3000;

        // middlewares
        this.middlewares();

        // set view
        this.setview();

        // app routes
        this.routes();

    }

    /**
     * Define the middlewares used by the app
     */
    middlewares() {
        // json encoder
        this.app.use(express.json());
        // body parser
        this.app.use(express.urlencoded({extended: true}));
        // public directory
        this.app.use(express.static("public"));
    }

    /**
     * Defines handdle bars as the view motor
     */
    setview() {
        this.app.set("view-engine", "hbs");
    }

    /**
     * Defines the routes used
     */
    routes() {
        this.app.use("/", routerIndex );
        this.app.use("/edit", routerEdit);
    }

    /**
     * Stars the server instance
     */
    listen() {
        this.app.listen(this.port, () => {
            console.log(`Server on port ${this.port}`);
        });
    }
}
