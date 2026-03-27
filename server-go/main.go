package main

import (
	"github.com/gin-gonic/gin"
)

func getHello(c *gin.Context) {
	c.String(200, "Hello, AlgoFER user!")
}

func main() {
	router := gin.Default()
	router.GET("/hello", getHello)

	router.Run("localhost:8080")
}
