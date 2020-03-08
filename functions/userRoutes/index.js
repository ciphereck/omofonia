const admin = require('firebase-admin');
const express = require('express')
const app = express.Router();
const database = admin.database();
const db = database.ref();
const googleUrl = 'https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=';
const request = require('request');
const jwt = require('jsonwebtoken');

exports.googleLogin = function(req, response) {

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

exports.isAuthenticated = function(req, res, next) {

	const token = req.headers.authorization;
  
	if (token) {
	  jwt.verify(token, "mudit", (err, data) => {
		if (err) {
  
		  res.status(401).json({
			success: false, err: 'unauthenticated request'
		  });
		}
		else {
		  let email = data.email;
		  email = email.slice(0, -10)
		  
		  req.body.email = email;
  
		  return next();
		}
	  });
	}
	else {
	  res.status(401).json({
		success: false, err: 'unauthenticated request'
	  });
	}
  };

  exports.isAuthenticatedAdmin = function(req, res, next) {
	const token = req.headers.authorization;
  
	if (token) {
	  jwt.verify(token, "mudit", (err, data) => {
		if (err) {
  
		  res.status(401).json({
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
				res.status(401).json({
				success: false, err: 'you are not an admin, please request admin rights'
				});
			}
  
		}
	  });
	}
	else {
	  res.status(401).json({
		success: false, err: 'unauthenticated request'
	  });
	}
  };