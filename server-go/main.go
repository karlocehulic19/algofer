package main

import (
	"github.com/gin-gonic/gin"
)

func getHello(c *gin.Context) {
	c.String(200, "Hello, AlgoFER user!")
}

type CodeSubmit struct {
	Code string `json:"code" binding:"required"`
}

func postDemoSolution(c *gin.Context) {
	var codeSubmit CodeSubmit

	if err := c.ShouldBindJSON(&codeSubmit); err != nil {
		c.String(400, "Invalid JSON")
		return
	}

	c.JSON(200, gin.H{
		"status":     "PASS",
		"codeSubmit": codeSubmit,
	})
}

func main() {
	router := gin.Default()
	router.GET("/hello", getHello)
	router.POST("/api/v1/oop/demo/submit", postDemoSolution)

	router.Run("localhost:8080")
}
