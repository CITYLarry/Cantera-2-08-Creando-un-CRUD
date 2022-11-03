import { Router } from "express";
import {renderIndex} from "../controllers/index.js";

export const router = Router();

router.get("/", renderIndex);
