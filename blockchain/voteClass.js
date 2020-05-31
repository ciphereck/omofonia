class Vote {
	constructor(electionId, candidateId, voterId) {
		this.candidateId = candidateId
		this.voterId = voterId
		this.electionId = electionId
	}
}

module.exports = {
	Vote: Vote
}