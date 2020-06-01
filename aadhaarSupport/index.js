const parser = require('xml2json');

module.exports.giveAadhaarJSON =  function (str, json) {
	const jsonFromXML = parser.toJson(str, {
		object: true,
		coerce: true,
	});

	const address = getAddress(jsonFromXML.OfflinePaperlessKyc.UidData.Poa)

	json.aadhaarName =  jsonFromXML.OfflinePaperlessKyc.UidData.Poi.name,
	json.dob = jsonFromXML.OfflinePaperlessKyc.UidData.Poi.dob,
	json.gender = jsonFromXML.OfflinePaperlessKyc.UidData.Poi.gender,
	json.address = address,
	json.hasedEmail =  jsonFromXML.OfflinePaperlessKyc.UidData.Poi.e,
	json.hashedMobileNo =  jsonFromXML.OfflinePaperlessKyc.UidData.Poi.m,
	json.picInJP2000 = jsonFromXML.OfflinePaperlessKyc.UidData.Pht

	return json
}

function getAddress(json) {
	const firstLine = json.careof + " " + json.house + " " + json.loc + " " + json.landmark
	const secondLine = json.street + " " + json.vtc + " " + json.po + " " + json.subdist 
	const thirdLine = json.dist + " " + json.state + " " + json.country + " " + json.pc
	return firstLine + " " + secondLine + " " + thirdLine
}