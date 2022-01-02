(* 1 *)
fun is_older (date1: int * int * int, date2: int * int * int) =
    #1 date1 < #1 date2
    orelse ((#1 date1 = #1 date2) andalso (#2 date1 < #2 date2))
    orelse ((#1 date1 = #1 date2) andalso (#2 date1 = #2 date2) andalso (#3 date1 < #3 date2))

(* 2 *)
fun number_in_month (dates: (int * int * int) list, month: int) =
    if null dates
    then 0
    else if #2 (hd dates) = month
    then number_in_month(tl dates, month) + 1
    else number_in_month(tl dates, month)
    (* let *)
    (* 	fun count (dates : (int * int * int) list, c : int) = *)
    (* 	    if null dates *)
    (* 	    then c *)
    (* 	    else if #2 (hd dates) = month *)
    (* 	    then count(tl dates, c + 1) *)
    (* 	    else count(tl dates, c) *)
    (* in *)
    (* 	count (dates, 0) *)
    (* end *)
			
(* 3 *)	
fun number_in_months (dates: (int * int * int) list, months: int list) =
    let
	fun count (months: int list) =
	    if null months
	    then 0
	    else count(tl months) + number_in_month(dates, hd months)
    in
	count(months)
    end

(* 4 *)
fun dates_in_month (dates: (int * int * int) list, month: int) =
    (* if null dates *)
    (* then [] *)
    (* else if #2 (hd dates) = month *)
    (* then (hd dates)::dates_in_month(t1 dates, month) *)
    (* else dates_in_month(tl dates, month) *)    
    let
    	fun append (dates: (int * int * int) list) =
    	    if null dates
    	    then []
    	    else if #2 (hd dates) = month
    	    then (hd dates) :: append(tl dates)
    	    else append(tl dates)
    in
    	append (dates)
    end

(* 5 *)
fun dates_in_months (dates: (int * int * int) list, months: int list) =
    let
	fun append (months: int list) =
	    if null months
	    then []
	    else dates_in_month(dates, hd months) @ append(tl months)
    in
	append months
    end

(* 6 *)
fun get_nth (ss: string list, n: int) =
    if n = 1
    then hd ss
    else get_nth(tl ss, n - 1)
		
(* 7 *)
fun date_to_string (date: int * int * int) =
    let
	val months = ["January", "February", "March", "April", "May", "June", "July",
		      "August", "September", "October", "November", "December"]		 
    in
	get_nth(months, #2 date) ^ " " ^ Int.toString(#3 date) ^ ", " ^ Int.toString(#1 date) 
    end

(* 8 *)
fun number_before_reaching_sum (sum: int, nums: int list) =
    let
	fun calculate (currentSum: int, n: int, nums: int list) =
	    if currentSum > sum orelse currentSum = sum
	    then n - 1
	    else calculate(currentSum + hd nums, n + 1, tl nums)
    in
	calculate(0, 0, nums)
    end

(* 9 *)
fun what_month (day: int) =
    let
	(* the question says 1-365, so assume Feb is 28 *)
	val daysInMonths = [31,28,31,30,31,30,31,31,30,31,30,31]
    in
	number_before_reaching_sum(day, daysInMonths) + 1
    end
	
(* 10 *)
fun month_range (day1: int, day2: int) =
    if day1 > day2
    then []
    else what_month(day1) :: month_range(day1 + 1, day2)
			  
(* 11 *)
fun oldest (dates: (int * int * int) list) =
    if null dates
    then NONE
    else
	let val currentOldest = oldest(tl dates)
	in
	    if isSome currentOldest andalso is_older(valOf currentOldest, hd dates)
	    then currentOldest
	    else SOME (hd dates)
	end

(* 12 Challenge *)
(* already did question 3 and 5 the "challenge" way *)

(* 13 Challenge *)
fun reasonable_date (date: int * int * int) =
    if #1 date < 0 orelse #2 date < 1 orelse #2 date > 12 orelse #3 date < 0
    then false
    else
	let
	    fun is_leap (year: int) =
		(year mod 400) = 0 orelse ((year mod 4) = 0 andalso (year mod 100) > 0)
	in
	    if #2 date = 2 andalso is_leap(#1 date)
	    then #3 date < 30
	    else if #2 date = 2
	    then #3 date < 29
	    else if ((#2 date - 1) mod 7) mod 2 = 0
	    then #3 date < 32
	    else #3 date < 31
	end
	    
						   
