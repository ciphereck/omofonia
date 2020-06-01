const admin = require('firebase-admin');
const express = require('express')
app = express.Router();
const database = admin.database();
const db = database.ref();
const googleUrl = 'https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=';
const request = require('request');
const jwt = require('jsonwebtoken');
const unirest = require("unirest");
const userRoutes = require("./userRoutes")

app.route('/profile').post(isAuthenticated, getProfile);
app.route('/otp').post(isAuthenticated, sendOtp);
app.route('/mobile').put(isAuthenticated ,updateMobile);
app.use('/', userRoutes)

function updateMobile(req, res) {
	console.log("Hi")
	mobileNo = req.body.mobileNumber;
	email = req.body.email
	console.log(req.body)
	json = req.body.data
	db
		.child('users')
		.child(email)
		.child("mobileNumber")
		.set(mobileNo)
		.then(() => {
			console.log("success in updating mobile")
			return res.send({
				"success": true,
				"message": "Mobile No updated successfully"
			})
		}).catch((err) => {
			console.log("error aa gayi" + err)
			return res.send({
				"success": false,
				"message": "error occured" + err
			})
		})
}

function sendOtp(req, res) {
	console.log(req.body)
	mobileNo = req.body.mobileNo;
	otp = req.body.otp;
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
					console.log("err" + r.error)
					if(r.error) return res.send({
						success: false,
						message: r.error
					})
					console.log("success in sending otp")
					return res.send({
						success: true,
						message: r.body
					})
				})
}

function getProfile(req, res) {
	email = req.body.email
	db
		.child('users')
		.child(email)
		.once('value')
		.then((snapshot) => {
			if(snapshot.val() === null) {

				return res.status(200).send({
					success: false,
					message: `${email} doesn't exist.`
				});
			}

			return res.status(200).send({
				"success": true,
				"data": snapshot.val(),
				"message": "Sent user data info successfully"
			})
		}).catch((err) => {
			return res.status(200).send({
				"success": false,
				"message": "error in getting firebase" + err,
			})
		})
}

function googleLogin(req, response) {
	request(googleUrl + req.body.idToken, {json: true}, (err, res, body) => {

		let data;
		if (err) {

			return response.status(400).json({success: false,err:err});
		}

		if (body.error_description !== undefined) {
			return response.status(401).json({
				message: "empty/invalid body received",
				error: 'unauthenticated request',
				success: false,
			});
		}
		let email1 = body.email;
		let email = email1.slice(0, -10)
		let email_child = "users/" + email;
		let ref = database.ref().child(email_child);
		
		ref.once('value')
		.then((snapshot) => {
			if (snapshot.val()) {

				jwttoken = snapshot.val();

				const token = jwt.sign(jwttoken, "mudit");
				data = {
					token: token,
					userInfo: jwttoken
				}
				return response.status(200).json({
					success: true, 
					data:data
				});
			}
			else {
				database.ref(email_child).set({
					email: body.email,
					name: body.name,
					photo: body.picture,
					admin: false,
				});

				jwttoken = {
					email: body.email,
					name: body.name,
					photo: body.picture,
					admin: false,
				};

				const token = jwt.sign(jwttoken, "mudit");
				data = {
					token: token,
					userInfo: jwttoken
				}

				return response.status(200).json({
					success: true, data:data
				});
			}
        });
	});
}

function isAuthenticated(req, res, next) {

	const token = req.body.authHeader;
  
	if (token) {
	  jwt.verify(token, "mudit", (err, data) => {
		if (err) {
  
		  return res.status(401).json({
			success: false, err: 'unauthenticated request'
		  });
		}
		else {
		  let email = data.email;
		  email = email.slice(0, -10)
		  
		  req.body.email = email;
		  req.body.data = data;
  
		  return next();
		}
	  });
	}
	else {
	  return res.status(401).json({
		success: false, err: 'unauthenticated request'
	  });
	}
};

function isAuthenticatedAdmin(req, res, next) {
	const token = req.body.authHeader;

	if (token) {
		jwt.verify(token, "mudit", (err, data) => {
		if (err) {

			return res.status(401).json({
				success: false, err: 'unauthenticated request'
			});
		}
		else {
			let adminStatus=data.admin;
			if(adminStatus === true)
				{
					let email = data.email;
					email = email.slice(0, -10)
					
					req.body.email = email;		
					return next();  
				}
			else {
				return res.status(401).json({
					success: false, err: 'you are not an admin, please request admin rights'
				});
			}

		}
		});
	}
	else {
		return res.status(401).json({
			success: false, err: 'unauthenticated request'
		});
	}
};

module.exports = {app, googleLogin}
