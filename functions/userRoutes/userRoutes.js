const admin = require('firebase-admin');
const express = require('express')
app = express.Router();
const database = admin.database();
const db = database.ref();
const request = require('request');
const jwt = require('jsonwebtoken');
const unirest = require("unirest");
const aadhaarFunctions = require("../aadhaarSupport")

app.route('/aadhaar').post(isAuthenticated, addAadhaar);

function addAadhaar(req, res) {
    console.log(req.body)
    console.log("yah aaya tha")
    jsonAadhar = req.body.data
    jsonAadhar = aadhaarFunctions.giveAadhaarJSON(req.body.aadhaarXml, jsonAadhar)
    // req.body.aadhaarXml = "hi"
    console.log(req.body)
    jsonAadhar.password = req.body.password
    jsonAadhar.aadhaarNumber = req.body.aadhaarNumber
    console.log(jsonAadhar)
    x = db
        .child("users")
        .child(req.body.email)
        
	x.set(jsonAadhar)
	.then((snapshot) => {
//		console.lof(snapshot.val())
	    x
		.once("value")
	 	.then((snapshot) => {
            		return res.send({
                		"success": true,
               	 		"message": "updated data successfully",
				"data": snapshot.val()
            	}).catch((err) => {
			return res.send({
				"success": false,
				"messsage": err
			})
		})
        })
        .catch((err) => {
            return res.send({
                success: false,
                message: err
            })
        })
   })
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

module.exports = app;
