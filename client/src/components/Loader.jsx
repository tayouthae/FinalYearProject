import React from "react";
import SyncLoader from "react-spinners/SyncLoader";


class Loader extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    return (
      <div className="sweet-loading">
        <SyncLoader
          size={30}
          color={"#515070"}
          loading={this.props.loading}
        />
      </div>
    );
  }
}
export default Loader;