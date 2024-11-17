import http from "../http-common"; 

class AnnouncementService {
  getAllAnnouncements(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/announcement/announcements`, searchDTO);
  }

  get(announcementId) {
    return this.getRequest(`/announcement/${announcementId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/announcement?field=${matchData}`, null);
  }

  addAnnouncement(data) {
    return http.post("/announcement/addAnnouncement", data);
  }

  update(data) {
  	return http.post("/announcement/updateAnnouncement", data);
  }
  
  uploadImage(data,announcementId) {
  	return http.postForm("/announcement/uploadImage/"+announcementId, data);
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

export default new AnnouncementService();
