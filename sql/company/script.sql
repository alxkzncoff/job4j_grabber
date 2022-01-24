-- В одном запросе получить:
-- - имена всех person, которые не состоят в компании с id = 5;
-- - название компании для каждого человека.
select p.name, c.name 
from person p inner
join company c
on p.company_id = c.id
where c.id != 5;

-- Необходимо выбрать название компании с максимальным 
-- количеством человек + количество человек в этой компании 
-- (нужно учесть, что таких компаний может быть несколько).
select c.name, count(p.name) as Количество_сотрудников
from person p inner
join company c 
on p.company_id = c.id
group by c.name
having count(p.name) = (select count(p.name) as Количество_сотрудников
						from person p inner
						join company c 
						on p.company_id = c.id
						group by c.name
						order by Количество_сотрудников desc limit 1);