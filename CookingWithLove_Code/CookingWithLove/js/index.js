const cardData = [
  {
    heading: "card 1",
    body: "this is card body",
  },
  {
    heading: "card 1",
    body: "this is card body",
  },
  {
    heading: "card 1",
    body: "this is card body",
  },
  {
    heading: "card 1",
    body: "this is card body",
  },
  {
    heading: "card 1",
    body: "this is card body",
  },
  {
    heading: "card 1",
    body: "this is card body",
  },
  {
    heading: "card 1",
    body: "this is card body",
  },
  {
    heading: "card 1",
    body: "this is card body",
  },
  {
    heading: "card 1",
    body: "this is card body",
  },
];

const postContainer = document.querySelector(".card-container");
const postMethods = () => {
  cardData.map((postData) => {
    const postElement = document.createElement("div");
    postElement.classList.add("card");
    postElement.innerHTML = `
    <h3 class="card-heading">${postData.heading}</h3>
    <p class="card-body">${postData.body}</p>
`;
    postContainer.appendChild(postElement);
  });
};
postMethods();
