import React, { useState } from "react";

import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet
} from "react-native";

export default function App() {

  const [count, setCount] = useState(0);

  const [isDarkMode,
         setIsDarkMode] =
         useState(false);

  const handleIncrement = () => {
    setCount(count + 1);
  };

  const handleDecrement = () => {

    if (count > 0) {
      setCount(count - 1);
    }
  };

  const handleReset = () => {
    setCount(0);
  };

  const toggleTheme = () => {
    setIsDarkMode(!isDarkMode);
  };

  return (

    <View
      style={[
        styles.container,
        {
          backgroundColor:
            isDarkMode
              ? "#222"
              : "#fff"
        }
      ]}
    >

      <Text
        style={[
          styles.counterText,
          {
            color:
              isDarkMode
                ? "#fff"
                : "#000"
          }
        ]}
      >
        {count}
      </Text>

      <View style={styles.row}>

        <TouchableOpacity
          style={styles.button}
          onPress={handleIncrement}
        >
          <Text>
            Increment
          </Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={styles.button}
          onPress={handleDecrement}
        >
          <Text>
            Decrement
          </Text>
        </TouchableOpacity>

      </View>

      <TouchableOpacity
        style={styles.button}
        onPress={handleReset}
      >
        <Text>
          Reset
        </Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={styles.button}
        onPress={toggleTheme}
      >
        <Text>
          Toggle Theme
        </Text>
      </TouchableOpacity>

    </View>
  );
}

const styles = StyleSheet.create({

  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center"
  },

  counterText: {
    fontSize: 50,
    marginBottom: 20
  },

  row: {
    flexDirection: "row"
  },

  button: {
    padding: 12,
    margin: 10,
    backgroundColor: "#ddd",
    borderRadius: 8
  }

});