console.log("Hi")
console.log(config.BASE)



function getDevelopers() {
	console.log("developers")
	const req = axios.get(config.BASE + "/voter/info?aadhaarNo=7206307664")	
	return req.then(token => {
		console.log(token.data)
		return token.data
	})
}