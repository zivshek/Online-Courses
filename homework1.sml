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
    8
