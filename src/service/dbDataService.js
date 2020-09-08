const API_URL = "http://localhost:8080";

const config = {
  headers: {
    crossDomain: true,
    "Access-Control-Allow-Origin": "*",
  },
};

class dbDataService {
  retreiveGenres() {
    return fetch(`${API_URL}/film/eng`, {
      data: {
        startDate: "1000-10-31",
        endDate: "2220-10-31",
        mood: "hohotach",
        genreIds: ["1", "3"],
      },
      mode: "no-cors",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json",
      },
    });
  }
}

export default new dbDataService();
