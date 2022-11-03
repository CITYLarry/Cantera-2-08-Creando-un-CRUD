import express from "express";
import bodyParser from "body-parser";
import {router as routerIndex} from "../routes/index.js";
import "hbs";

export default class Server {
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

    middlewares() {
        // json encoder
        this.app.use(bodyParser.json());
        // body parser
        this.app.use(bodyParser.urlencoded({extended: true}));
        // public directory
        this.app.use(express.static("public"));
    }

    setview() {
        this.app.set("view-engine", "hbs");
    }

    routes() {
        this.app.use("/", routerIndex );
    }

    listen() {
        this.app.listen(this.port, () => {
            console.log(`Server on port ${this.port}`);
        });
    }
}
