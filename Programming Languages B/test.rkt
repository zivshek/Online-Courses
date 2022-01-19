#lang racket

(provide (all-defined-out))

(define (my-sum xs)
  (if (null? xs)
      0
      (+ (car xs) (my-sum (cdr xs)))))

(define (my-map f xs)
  (if (null? xs)
      null
      (cons (f (car xs))
            (my_map f (cdr xs)))))