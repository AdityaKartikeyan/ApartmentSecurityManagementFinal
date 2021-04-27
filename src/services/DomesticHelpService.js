import axios from "axios";

const DOMESTICHELP_API_BASE_URL = "http://localhost:8080/DomesticHelp/";

class DomesticHelpService {
  getDomesticHelp() {
    return axios.get(DOMESTICHELP_API_BASE_URL + "getAll");
  }
  createDomesticHelp(domestichelp) {
    return axios.post(
      DOMESTICHELP_API_BASE_URL + "addDomesticHelp",
      domestichelp
    );
  }
  updateDomesticHelp(domestichelp, domestichelpId) {
    return axios.put(
      DOMESTICHELP_API_BASE_URL + "updateDomesticHelp/" + domestichelpId,
      domestichelp
    );
  }
  getDomesticHelpByDomesticHelpId(domestichelpId) {
    return axios.get(DOMESTICHELP_API_BASE_URL + "getById/" + domestichelpId);
  }
  deleteDomesticHelp(id) {
    return axios.delete(DOMESTICHELP_API_BASE_URL + "deleteDomesticHelp/" + id);
  }
}

export default new DomesticHelpService();
