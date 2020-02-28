const admin = require('firebase-admin');

const express = require('express')
const app = express.Router();

const database = admin.database();
const db = database.ref();

app.route('/create')
		.get(createElection)


function createElection(req, res) {
	db
		.child('election')
		.once('value')
		.then((snapshot) => {
			if(snapshot.val() == null) {
				return res.send({
					"success": false,
					"message": "cant access election db"
				})
			}
			id = snapshot.val().length

			db
				.child('election')
				.child(id)
				.set({
					"candidate": [{
						"name": "Dummy candidate",
						"aadhaarNo": "777777777777",
						"picUrl": "https://imagevars.gulfnews.com/2019/08/14/Prime-Minister-Narendra-Modi_16c906a7fc4_large.jpg",
						"party": {
							"name": "Dummy Party",
							"partyUrl": "https://en.wikipedia.org/wiki/Bharatiya_Janata_Party",
							"partySign": "https://upload.wikimedia.org/wikipedia/en/thumb/1/1e/Bharatiya_Janata_Party_logo.svg/1200px-Bharatiya_Janata_Party_logo.svg.png",
						}
					}],
					"id": id,
					"status": 0,
					"totalVotesCasted": 0
				})
				.then((snapshot) => {
					return res.send({
						"success": true,
						"message": "created election successfully"
					})
				}).catch((err) => {
					return res.send({
						"success": false,
						"message": "error occured",
						"error": err
					})
				})
		})
}

module.exports = app