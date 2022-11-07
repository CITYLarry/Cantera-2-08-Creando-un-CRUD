import {} from "dotenv/config";

import Server from "./models/server.js";

/**
 * Intsance of the clas Server
 */
const server = new Server();

/**
 * Server initialization
 */
server.listen();
