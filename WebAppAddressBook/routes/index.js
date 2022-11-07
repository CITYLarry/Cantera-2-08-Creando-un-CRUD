import { Router } from "express";
import { renderIndex, postIndex, deleteIndex } from "../controllers/index.js";

/**
 * Instance of the element Router
 */
export const router = Router();

/**
 * Handle the get method for the index
 */
router.get("/", renderIndex);

/**
 * Handle de post method fo the index
 */
router.post("/", postIndex);

/**
 * Handle the redirection method for a delete CRUD action
 */
router.get("/delete/:id", deleteIndex);

