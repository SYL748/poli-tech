import axios from "axios";

const API = axios.create();

API.interceptors.response.use(
	(response) => response.data
);

export default API;