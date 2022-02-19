package main

import (
	"encoding/json"
	"net/http"
)

type Test struct {
	//The JSON key name should be message
	Message string `json:"msg"`
	//Capital vars are exported and small vars are not
}

func test(w http.ResponseWriter, _ *http.Request) {
	body := Test{Message: "SUP"}
	err := json.NewEncoder(w).Encode(body)
	if err != nil {
		w.WriteHeader(500)
	}

}
