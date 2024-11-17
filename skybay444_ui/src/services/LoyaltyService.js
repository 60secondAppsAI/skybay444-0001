import http from "../http-common"; 

class LoyaltyService {
  getAllLoyaltys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/loyalty/loyaltys`, searchDTO);
  }

  get(loyaltyId) {
    return this.getRequest(`/loyalty/${loyaltyId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/loyalty?field=${matchData}`, null);
  }

  addLoyalty(data) {
    return http.post("/loyalty/addLoyalty", data);
  }

  update(data) {
  	return http.post("/loyalty/updateLoyalty", data);
  }
  
  uploadImage(data,loyaltyId) {
  	return http.postForm("/loyalty/uploadImage/"+loyaltyId, data);
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

export default new LoyaltyService();
