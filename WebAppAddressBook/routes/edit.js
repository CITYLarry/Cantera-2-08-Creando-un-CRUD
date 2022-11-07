import { Router } from "express";
import { renderEdit, postEdit } from "../controllers/edit.js";

/**
 * Instance of the element Router
 */
export const router = Router();

/**
 * Handdle the get method for the edit-index view
 */
router.get("/:id", renderEdit);

/**
 * Handdle the post method fot the edit-index view
 */
router.post("/:id", postEdit);
