const admin = require('firebase-admin');

const express = require('express');
const app = express.Router();

const database = admin.database();
const db = database.ref();


app.route('/info')
		.get(getInfo)
		.post(addInfo);


function getInfo(req, res) {
	query = req.query;
	console.log(query);
	res.send(query)
}

function addInfo(req, res) {
	params = req.body;
	console.log(params);
	res.send(params);
}


module.exports = app