import React, { Component } from "react";
import Select from "react-select";
import makeAnimated from "react-select/animated";
import axios from "axios";
import "../../node_modules/video-react/dist/video-react.css";
import { Text } from "../containers/Language";

import ScrollMagic from "../../node_modules/scrollmagic";
const genreoptions = [
  { value: "1", label: "Comedy" },
  { value: "2", label: "Sci-fi" },
  { value: "3", label: "Horror" },
  { value: "4", label: "Romance" },
  { value: "5", label: "Thriller" },
  { value: "6", label: "Drama" },
  { value: "7", label: "Mystery" },
  { value: "8", label: "Crime" },
  { value: "9", label: "Animation" },
  { value: "10", label: "Adventure" },
  { value: "11", label: "Fantasy" },
  { value: "12", label: "Comedy-Romance" },
  { value: "13", label: "Action-Comedy" },
  { value: "14", label: "Superhero" },
];

const moodoptions = [
  { value: "tense", label: "Tense-nervous" },
  { value: "irritated", label: "Irritated-Annoyed" },
  { value: "bored", label: "Bored-Weary" },
  { value: "gloomy", label: "Gloomy-Sad" },
  { value: "excited", label: "Excited-Lively" },
  { value: "cheerful", label: "Cheerful-Happy" },
  { value: "relaxed", label: "Relaxed-Carefree" },
  { value: "calm", label: "Calm-Serene" },
];

const years = [
  { value: "1940-12-31", label: 1940 },
  { value: "1950-12-31", label: 1950 },
  { value: "1960-12-31", label: 1960 },
  { value: "1970-12-31", label: 1970 },
  { value: "1980-12-31", label: 1980 },
  { value: "1990-12-31", label: 1990 },
  { value: "2000-12-31", label: 2000 },
  { value: "2010-12-31", label: 2010 },
  { value: "2020-12-31", label: 2020 },
];

class Generator extends Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedOption: null,
      yearsIsChecked: false,
      yearStart: "1940-12-31",
      yearEnd: "2220-12-12",
      mood: null,
      genreIds: null,
      isLoaded: false,
      randomMovie: {},
    };
    this.yearStartChanged = this.yearStartChanged.bind(this);
    this.yearEndChanged = this.yearEndChanged.bind(this);
    this.moodChanged = this.moodChanged.bind(this);
    this.genreChanged = this.genreChanged.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.toggleCheckboxChange = this.toggleCheckboxChange.bind(this);
  }

  toggleCheckboxChange = () => {
    this.setState(({ yearsIsChecked }) => ({
      yearsIsChecked: !yearsIsChecked,
    }));
    console.log("state changed to", this.state.yearsIsChecked);
  };

  yearStartChanged(event) {
    this.setState({ yearStart: event.value });
    console.log("The state was changed to ", this.state.yearStart, event.value);
  }

  yearEndChanged(event) {
    this.setState({ yearEnd: event.value });
    console.log("The state was changed to ", this.state.yearEnd, event.value);
  }

  moodChanged(mood) {
    this.setState({ mood });
    console.log("The mood was changed to ", this.state.mood, mood);
  }

  genreChanged(genreIds) {
    this.setState({ genreIds });
    console.log("The genre was changed to ", this.state.genreIds, genreIds);
  }

  handleSubmit() {
    this.setState({ isLoaded: true });
    let arrGenres = null;
    let mood = null;
    if (this.state.genreIds) {
      arrGenres = this.state.genreIds.map((item) => item.value);
    }
    if (this.state.mood) {
      mood = this.state.value;
    }
    axios
      .post(`http://localhost:8080/film/eng`, {
        startDate: this.state.yearStart,
        endDate: this.state.yearEnd,
        mood: mood,
        genreIds: arrGenres,
      })
      .then((response) => {
        if (response.data) {
          console.log("response: ", response.data);
          this.setState({ randomMovie: response.data });
        }
      })
      .then(
        console.log(this.state.randomMovie),
        this.setState({ isLoaded: true })
      )
      .catch((error) => {
        console.log(error.response);
      });
  }

  componentWillMount() {
    console.log(this.state.yearStart, "year start value");
  }

  getGenres() {
    this.setState({ isLoaded: false });
    this.state.randomMovie.genres.map((item) => (
      <h6>{genreoptions[item].value}</h6>
    ));
    this.setState({ isLoaded: true });
  }

  render() {
    var controller = new ScrollMagic.Controller();
    var scene = new ScrollMagic.Scene({
      triggerElement: "#animatedBlock",
    })
      .setClassToggle("#animatedBlock", "fade-in")
      .addTo(controller);
    return (
      <div>
        {this.state.isLoaded ? (
          <div className="block-div2 randomLoaded">
            <div className="blockRandom">
              <div className="movieRandom">
                <div className="randomMovieTitle">Your mood highlight!</div>
                <div className="randomMovieInfo">
                  <div className="moviePicture">
                    <img
                      className="moviePoster"
                      src={this.state.randomMovie.imageUrl}
                    />
                  </div>
                  <div className="movieInfo">
                    <p className="page-text24">{this.state.randomMovie.name}</p>
                    <p className="page-text16">
                      <Text tid="mood" />: {this.state.randomMovie.mood}
                    </p>
                    <p className="page-text16">
                      <Text tid="release_date" />:{" "}
                      {this.state.randomMovie.releaseDate}
                    </p>
                    <p className="page-text16">
                      <Text tid="genre" />:{" "}
                    </p>
                    <p className="page-text16">
                      <Text tid="description" />:{" "}
                      {this.state.randomMovie.description}
                    </p>
                  </div>
                </div>
                <div className="trailerBlock">
                  <span className="trailer-word page-text16 ">
                    <Text tid="trailer" />
                  </span>
                  <div className="trailer">
                    <iframe
                      src={this.state.randomMovie.trailerUrl}
                      width="100%"
                      height="100%"
                      allowFullScreen={true}
                      scrolling="no"
                      frameBorder="no"
                    ></iframe>
                  </div>
                </div>
              </div>
            </div>
            <div className="trailerRandom"></div>
          </div>
        ) : (
          <div className="block-div3">
            <div id="animated-block" className="block-div3 select-block">
              <div>
                <div className="text-div">
                  <span className="page-text24">
                    <Text tid="make_random" />
                    <br />
                    <Text tid="choose_what" />
                  </span>
                </div>
                <div className="block-select">
                  <span className="page-text16">
                    <Text tid="genre"></Text>
                  </span>
                  <Select
                    className="select-style"
                    options={genreoptions}
                    onChange={this.genreChanged}
                    components={makeAnimated()}
                    value={this.state.genreIds}
                    isMulti
                  />
                  <span className="page-text16">
                    <Text tid="mood" />
                  </span>
                  <Select
                    className="select-style"
                    options={moodoptions}
                    onChange={this.moodChanged}
                    components={makeAnimated()}
                    value={this.state.mood}
                  />
                </div>
                <div className="block-select">
                  <div className="checkb">
                    <span className="page-text16">
                      <Text tid="time_period" />
                    </span>
                    <input
                      type="checkbox"
                      checked={this.yearsIsChecked}
                      onChange={this.toggleCheckboxChange}
                    />
                  </div>
                  {this.state.yearsIsChecked ? (
                    <div className="select-year">
                      <div className="inyear">
                        <span className="page-text16">
                          <Text tid="from" />
                        </span>
                        <Select
                          className="select-style year"
                          options={years}
                          onChange={this.yearStartChanged}
                        ></Select>
                      </div>
                      <div className="inyear">
                        <span className="page-text16">
                          <Text tid="to" />
                        </span>
                        <Select
                          className="select-style year"
                          options={years}
                          onChange={this.yearEndChanged}
                        ></Select>
                      </div>
                    </div>
                  ) : (
                    <span className="page-text16">
                      <Text tid="search_for_all" />
                    </span>
                  )}
                </div>
                <button
                  type="button"
                  className="random-buttom"
                  value="Search"
                  onClick={this.handleSubmit}
                >
                  <Text tid="search" />
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
    );
  }
}

export default Generator;
