const admin = require('firebase-admin');
const unirest = require("unirest");
const express = require('express');
const app = express.Router();

app.route('/')
		.get(sendOtp);


function sendOtp(req, res) {
	mobileNo = req.query.mobileNo;
	otp = req.query.otp;
	const message = "Your otp is " + otp;
	
	return unirest
				.post("https://www.fast2sms.com/dev/bulk")
				.headers({
					"authorization": "cF301XeZAe2eTxMzMjSz7I6lYblsgIdQUh8so2ZYQwrHsfSMYXVBukYuNtGr"
				})
				.form({
					"sender_id": "FSTSMS",
				    "message": message,
				    "language": "english",
				    "route": "p",
				    "numbers": mobileNo,
				})
				.end(function(r) {
					if(r.error) return res.send(r.error)
					return res.send(r.body)
				})
}


module.exports = app