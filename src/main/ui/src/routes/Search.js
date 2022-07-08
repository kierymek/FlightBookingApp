import { useState } from "react";
import Confirm from "../components/Confirm";
import Flight from "../components/Flight";
import Locate from "../components/Locate";
import Order from "../components/Order";

import "../css/search.scss";

function Search() {
  const [origin, setOrigin] = useState();
  const [destination, setDestination] = useState();
  const [flight, setFlight] = useState();
  const [confirmation, setConfirmation] = useState();
  const [order, setOrder] = useState();

  console.log(
    "Search was called with params values: ",
    origin,
    destination,
    flight,
    confirmation,
    order
  );

  return (
    <div className="search">
      <h2>Search for flights</h2>
      <Locate handleChoice={setDestination} display={"Origin"} />
      <Locate handleChoice={setOrigin} display={"Destination"} />
      {origin && destination && (
        <Flight
          origin={origin}
          destination={destination}
          setFlight={setFlight}
        />
      )}
      {flight && <Confirm flight={flight} setConfirmation={setConfirmation} />}
      {confirmation && (
        <Order confirmation={confirmation} order={order} setOrder={setOrder} />
      )}
    </div>
  );
}

export default Search;
