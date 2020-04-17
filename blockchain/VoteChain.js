const vote = require('./voteClass')
const blockchain = require('./blockchain')

class VoteChain {
    constructor() {
        this.voteChain = []
        console.log("Vote Chain Instanitated")
    }

    addnewBlockChain() {
        const chain = new blockchain.Blockchain()
        const index = this.voteChain.length
        this.voteChain.push(chain)
        return index
    }

    addVote(index) {
        if(index >= this.voteChain.length) {
            console.log("Invalid Election Id")
            return null
        }

        const max = 100
        const voteData = new vote.Vote(Math.floor(Math.random() * max), Math.floor(Math.random() * max), Math.floor(Math.random() * max))
        const newBlock = this.voteChain[index].generateNextBlock(voteData)
        this.voteChain[index].addBlock(newBlock)
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
                voteCount.set(candidateId, voteCount.get(candidateId))
            }else {
                voteCount.set(candidateId, 1)
            }
        }
        return voteCount
    }
}

voteChain = new VoteChain()
voteChain.addnewBlockChain()
voteChain.addVote(0)
voteChain.addnewBlockChain()
voteChain.addVote(0)
voteChain.addVote(0)
voteChain.addVote(1)
console.log(voteChain.getSpecificBlockChain(0))
console.log(voteChain.getSpecificBlockChain(1))
console.log(voteChain.voteChain)
console.log(voteChain.countVotes(0))

module.exports = {
	VoteChain: VoteChain
}