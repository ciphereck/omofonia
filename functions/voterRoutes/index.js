const admin = require('firebase-admin');

const express = require('express');
const app = express.Router();

const database = admin.database();
const db = database.ref();
const aadharFunctions = require('./../aadhaarSupport/index.js')


app.route('/info')
		.get(getInfo)
		.post(addInfo);

app.route('/otp')
		.post(sendOtp);


app.route('/aadhaar')
		.post(getAadhaarDetails)


function getAadhaarDetails(req, res) {
	aadhaarXML = req.body.aadhaarXML
	console.log(aadhaarXML)
	console.log(aadharFunctions.giveAadhaarJSON(aadhaarXML))
}


function getInfo(req, res) {
	aadhaarNo = req.query.aadhaarNo;
	
	db
		.child('voter')
		.child(aadhaarNo)
		.once('value')
		.then((snapshot) => {
			if(snapshot.val() === null) {

				return res.status(200).send({
					success: false,
					message: `${aadhaarNo} doesn't exist.`
				});
			}

			return res.status(200).send({
				"success": true,
				"data": snapshot.val(),
				"message": "Sent voter info successfully"
			})
		})
		.catch((err) => {
			return res.status(200).send({
				"success": false,
				"message": "error in getting firebase",
			})
		})
}

function addInfo(req, res) {
	voterData = req.body.voterData;
	
	db
		.child('voter')
		.child(voterData.aadhaarNo)
		.set(voterData)
		.then((snapshot) => {
			return res.status(200).send({
				success: true,
				message: `Added ${voterData.aadhaarNo} successfully`
			});
		})
		.catch((err) => {
			return res.status(200).send({
				success: false,
				message: "error occured"
			})
		})
}

function sendOtp(req, res) {
	console.log(req.url)
	aadhaarNo = req.body.aadhaarNo;
	db
		.child('voter')
		.child(aadhaarNo)
		.once('value')
		.then((snapshot) => {
			if(snapshot.val() == null) {

				return res.status(200).send({
					success: false,
					message: `${aadhaarNo} doesn't exist.`
				});
			}
			let url = "../otp?mobileNo=" + snapshot.val().mobileNo + "&otp=" + req.body.otp;
			return res.redirect(url);
		})
}

module.exports = app