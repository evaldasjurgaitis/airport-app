import { ColumnProps } from 'antd/es/table';

import { AirportDetail, ProviderDetail } from '../../../typing';

const getAirportTableColumns: ColumnProps<AirportDetail>[] = [
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Municipality',
        dataIndex: 'municipality',
        key: 'municipality',
    },
    {
        title: 'Price',
        dataIndex: 'providerDetail',
        key: 'providerDetail',
        render: (providerDetail: ProviderDetail) => {
            if (providerDetail) {
                return `${providerDetail.name.toUpperCase()} ${providerDetail.price} ${providerDetail.currency}`;
            }
            return 'N/A';
        },
    },
];

export default getAirportTableColumns;
