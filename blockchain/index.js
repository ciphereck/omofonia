const express = require('express')

const app = express();
const http_port = process.env.HTTP_PORT || 3001;



app.listen(http_port, () => console.log("Http Routes Listening on port: ", http_port))