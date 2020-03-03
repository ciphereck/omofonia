const parser = require('xml2json');

module.exports.giveAadhaarJSON =  function (str) {
	const jsonFromXML = parser.toJson(str, {
		object: true,
		coerce: true,
	});

	const address = getAddress(jsonFromXML.OfflinePaperlessKyc.UidData.Poa)

	const aadhaarJSON = {
		name: jsonFromXML.OfflinePaperlessKyc.UidData.Poi.name,
		dob: jsonFromXML.OfflinePaperlessKyc.UidData.Poi.dob,
		gender: jsonFromXML.OfflinePaperlessKyc.UidData.Poi.gender,
		address: address,
		hasedEmail: jsonFromXML.OfflinePaperlessKyc.UidData.Poi.e,
		hashedMobileNo: jsonFromXML.OfflinePaperlessKyc.UidData.Poi.m,
		picInJP2000: jsonFromXML.OfflinePaperlessKyc.UidData.Pht
	}

	return aadhaarJSON
}

function getAddress(json) {
	const firstLine = json.careof + " " + json.house + " " + json.loc + " " + json.landmark
	const secondLine = json.street + " " + json.vtc + " " + json.po + " " + json.subdist 
	const thirdLine = json.dist + " " + json.state + " " + json.country + " " + json.pc
	return firstLine + " " + secondLine + " " + thirdLine
}