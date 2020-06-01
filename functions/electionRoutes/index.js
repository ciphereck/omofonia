const admin = require('firebase-admin');

const express = require('express')
const app = express();

const database = admin.database();
const db = database.ref();

const modifyRoutes = require('./electionCreate')

app.route('/change').post(updateElection)

function updateElection(req, res) {
	console.log(req.body)
	db
		.child('election')
		.set(JSON.parse( JSON.stringify(req.body ) ))
		.then(() => {
			return res.send({
				"success": true
			})
		})
		.catch((err) => {
			return res.send({
				"success": false,
				message: err
			})
		})
}

app.use('/modify', modifyRoutes)

app.route('/info')
		.get(getElectionInfo)


function getElectionInfo(req, res) {
	electionId = req.query.electionId

	if(electionId == null) {
		return getAllElectionInfo(res)
	}
	return getParticularElectionInfo(res, electionId)

}
 
function getAllElectionInfo(res) {
	db
		.child('election')
		.once('value')
		.then((snapshot) => {
			if(snapshot.val() == null) {
				return res.json({
					"success": false,
					"message": "No data till now!"
				})
			}
			return res.send({
				"success": true,
				"message": "Sent election data successfully",
				"data": snapshot.val()
			})
		})
}

function getParticularElectionInfo(res, id) {
	db
		.child('election')
		.child(id)
		.once('value')
		.then((snapshot) => {
			if(snapshot.val() == null) {
				return res.json({
					"success": false,
					"message": "No data till now!"
				})
			}
			return res.send({
				"success": true,
				"message": "Sent election data successfully",
				"data": snapshot.val()
			})
		})
}

module.exports = app
