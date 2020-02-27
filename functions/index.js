const admin = require('firebase-admin');
const functions = require('firebase-functions');
const express = require('express');
const cors = require('cors');

admin.initializeApp();

const app = express();

app.use(cors({ origin: true }));

app.use('/scxaaaa', (req, res) => {

    res.send("saaasasasasds");
})

app.use('/developers', (req, res) => {
	res.status(200).json({
		"developers": ["Mudit", "Navjot", "Ekta"]
	})
})

const voterRoutes = require('./voterRoutes')
const otpRoutes = require('./otpRoutes');

app.use('/voter', voterRoutes);
app.use('/otp', otpRoutes)

exports.api = functions.https.onRequest(app);

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });
