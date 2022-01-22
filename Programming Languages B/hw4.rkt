#lang racket

(provide (all-defined-out)) ;; so we can put tests in a second file

;; put your code below
; 1
(define (sequence low high stride)
  (if (> low high)
      null
      (cons low (sequence (+ low stride) high stride))))

; 2
(define (string-append-map xs suffix)
  (map (lambda (s) (string-append s suffix)) xs))

; 3
(define (list-nth-mod xs n)
  (cond [(< n 0) (error "list-nth-mod: negative number")]
        [(empty? xs) (error "list-nth-mod: empty list")]
        [#t (car (list-tail xs (remainder n (length xs))))]))

; 4
(define (stream-for-n-steps s n)
  (letrec ([f (lambda (s ans)
                (let ([pr (s)])
                  (if (= (length ans) n)
                      ans
                      (f (cdr pr) (cons (car pr) ans)))))])
    (f s '())))