echo  "Do you want to delete the containers? (y/n)"
read answer

if [ "$answer" = "${answer#[Yy]}" ] ;then
    echo "Cancelled"
    exit 1
fi

echo "Deleting containers..."

docker rm prometheus
echo "Container 'prometheus' deleted."

docker rm grafana
echo "Container 'grafana' deleted."

docker rm quarkus
echo "Container 'quarkus' deleted."

echo "Containers stopped and deleted."
