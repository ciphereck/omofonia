const CryptoJS = require("crypto-js");

class Block {
	constructor(index, previousHash, timestamp, voteData) {
		this.index = index
		this.previousHash = previousHash
		this.voteData = voteData
		this.timestamp = timestamp
		this.hash = this.calculateHash(index, previousHash, timestamp, voteData)
		// console.log(this)
	}

	calculateHashForBlock() {
		return this.calculateHash(this.index, this.previousHash, this.timestamp, this.voteData)
	}

	calculateHash(index, previousHash, timestamp, voteData) {
		return CryptoJS.SHA256(index + previousHash + timestamp + voteData).toString()
	}
}

module.exports = {
	Block: Block
}