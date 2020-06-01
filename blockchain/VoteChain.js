const vote = require('./voteClass')
const blockchain = require('./blockchain')

class VoteChain {
    constructor() {
        this.voteChain = []
        console.log("Vote Chain Instanitated")
    }

    getLength() {
        return this.voteChain.length
    }

    getVoteChain() {
        return this.voteChain
    }

    addnewBlockChain() {
        const chain = new blockchain.Blockchain()
        const index = this.voteChain.length
        this.voteChain.push(chain)
        return index
    }

    addVote(electionId, candidateId, voterId) {
        while(this.voteChain.length <= electionId) {
            this.addnewBlockChain()
        }
        if(electionId >= this.voteChain.length) {
            console.log("Invalid Election Id")
            return null
        }
        blk = this.voteChain.getSpecificBlockChain(electionId)
        for(var i=1; i<blk.length; i++) {
            if(blk[i].voteData.voterId == voterId) {
                return false
            }
        }

        const voteData = new vote.Vote(electionId, candidateId, voterId)
        const newBlock = this.voteChain[electionId].generateNextBlock(voteData)
        return this.voteChain[electionId].addBlock(newBlock)
    }

    getSpecificBlockChain(index) {
        if(index >= this.voteChain.length) {
            console.log("Invalid Election Id")
            return null
        }

        return this.voteChain[index]
    }

    countVotes(index) {
        if(index >= this.voteChain.length) {
            console.log("Invalid Election Id")
            return null
        }

        const blockchain = this.getSpecificBlockChain(index).getBlockchain()
        const voteCount = new Map()
        for(var i=1; i<blockchain.length; i++) {
            const candidateId = blockchain[i].voteData.candidateId
            if(voteCount.has(candidateId) == true) {
                voteCount.set(candidateId, voteCount.get(candidateId)+1)
            }else {
                voteCount.set(candidateId, 1)
            }
        }
        const jsonVoteCount = []
        voteCount.forEach( (value, key) => {
            jsonVoteCount.push({
                "candidateId": key,
                "voteCount": value
            })
        })
        console.log(jsonVoteCount)
        return jsonVoteCount
    }
}

// voteChain = new VoteChain()
// voteChain.addnewBlockChain()
// voteChain.addVote(0)
// voteChain.addnewBlockChain()
// voteChain.addVote(0)
// voteChain.addVote(0)
// voteChain.addVote(1)
// console.log(voteChain.getSpecificBlockChain(0))
// console.log(voteChain.getSpecificBlockChain(1))
// console.log(voteChain.voteChain)
// console.log(voteChain.countVotes(0))

module.exports = {
	VoteChain: VoteChain
}