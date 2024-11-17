import http from "../http-common"; 

class PassportService {
  getAllPassports(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/passport/passports`, searchDTO);
  }

  get(passportId) {
    return this.getRequest(`/passport/${passportId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/passport?field=${matchData}`, null);
  }

  addPassport(data) {
    return http.post("/passport/addPassport", data);
  }

  update(data) {
  	return http.post("/passport/updatePassport", data);
  }
  
  uploadImage(data,passportId) {
  	return http.postForm("/passport/uploadImage/"+passportId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new PassportService();
