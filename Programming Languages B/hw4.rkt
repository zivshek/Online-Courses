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
  (if (= n 0)
      null
      (let ([pr (s)])
        (cons (car pr) (stream-for-n-steps (cdr pr) (- n 1))))))

; 5
(define funny-number-stream
  (letrec ([f (lambda (x)
                (cons (if (= (remainder x 5) 0) (- x) x)
                      (lambda () (f (+ x 1)))))])
    (lambda () (f 1))))

; 6
(define dan-then-dog
  (letrec ([f (lambda (n)
                (cons (if (= (remainder n 2) 0) "dan.jpg" "dog.jpg")
                      (lambda () (f (+ n 1)))))])
    (lambda () (f 0))))

; 7
(define (stream-add-zero s)
  (lambda ()
    (let ([pr (s)])
      (cons (cons 0 (car pr)) (stream-add-zero (cdr pr))))))

; 8
(define (cycle-lists xs ys)
  (letrec ([f (lambda (n)
                (cons (cons (list-nth-mod xs n) (list-nth-mod ys n))
                      (lambda () (f (+ n 1)))))])
    (lambda () (f 0))))

; 9
(define (vector-assoc v vec)
  (letrec ([f (lambda (vec)
                (cond [(or (null? vec) (= (vector-length vec) 0)) #f]
                      [#t (letrec ([len (vector-length vec)]
                                   [hd (vector-ref vec 0)]
                                   [tail (vector-take-right vec (- len 1))])
                          (if (and (pair? hd) (= (car hd) v)) hd (f tail)))]))])
    (f vec)))
                      
; 10
(define (cached-assoc xs n)
  (letrec ([cache (make-vector n #f)]
           [count 0]
           [f (lambda (v)
                (let ([result (vector-assoc v cache)])
                  (if (equal? result #t)
                      result
                      (let ([new-result (assoc v xs)])
                        (begin
                          (if (equal? new-result #t)
                              (begin
                                (vector-set! cache (remainder count n) new-result)
                                (set! count (+ count 1))
                                new-result)
                              new-result))))))])
    f))
    
                      
                  