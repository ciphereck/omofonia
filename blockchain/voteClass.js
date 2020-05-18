class Vote {
	constructor(candidateId, voterId, electionId) {
		this.candidateId = candidateId
		this.voterId = voterId
		this.electionId = electionId
	}
}

module.exports = {
	Vote: Vote
}